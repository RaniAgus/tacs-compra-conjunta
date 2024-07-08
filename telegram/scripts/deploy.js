const { Telegram } = require('telegraf');
const commands = require('../bot/commands');

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
  errors.push('Expected usage: npm run bot:deploy <WEBHOOK_URL>');
}

if (errors.length > 0) {
  console.error(errors.join('\n'));
  process.exit(1);
}

const telegram = new Telegram(TELEGRAM_BOT_TOKEN);

telegram.setWebhook(WEBHOOK_URL, {
  secret_token: TELEGRAM_WEBHOOK_SECRET,
}).then(() => {
  console.log('Webhook set successfully: ', WEBHOOK_URL);
}).catch(err => {
  console.error('Error setting webhook: ', err);
});

telegram.setMyCommands(
  Object.entries(commands).map(([command, { description }]) => ({ command, description }))
).then(() => {
  console.log('Commands set successfully');
}).catch(err => {
  console.error('Error setting commands: ', err);
});
