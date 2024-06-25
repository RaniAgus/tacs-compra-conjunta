const { Telegram } = require('telegraf');

const TELEGRAM_BOT_TOKEN      = process.env.TELEGRAM_BOT_TOKEN,
      TELEGRAM_WEBHOOK_SECRET = process.env.TELEGRAM_WEBHOOK_SECRET,
      WEBHOOK_URL             = process.argv[2];

const errors = [];

if (!TELEGRAM_WEBHOOK_SECRET) {
  errors.push('Missing environment variable: TELEGRAM_WEBHOOK_SECRET');
}

if (!TELEGRAM_BOT_TOKEN) {
  errors.push('Missing environment variable: TELEGRAM_BOT_TOKEN');
}

if (!WEBHOOK_URL) {
  errors.push('Expected usage: npm run set:webhook <WEBHOOK_URL>');
}

if (errors.length > 0) {
  console.error(errors.join('\n'));
  process.exit(1);
}

const telegram = new Telegram(TELEGRAM_BOT_TOKEN);

telegram.setWebhook(WEBHOOK_URL, { secret_token: TELEGRAM_WEBHOOK_SECRET })
   .then(() => {
     console.log('Webhook set successfully: ', WEBHOOK_URL);
   })
   .catch(err => {
     console.error('Error setting webhook: ', err);
   });
