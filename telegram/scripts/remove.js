const { Telegram } = require('telegraf');

const TELEGRAM_BOT_TOKEN      = process.env.TELEGRAM_BOT_TOKEN;

if (!TELEGRAM_BOT_TOKEN) {
  console.error('Missing environment variable: TELEGRAM_BOT_TOKEN');
  process.exit(1);
}

const telegram = new Telegram(TELEGRAM_BOT_TOKEN);

telegram.deleteWebhook({
  drop_pending_updates: true,
}).then(() => {
  console.log('Webhook deleted successfully');
}).catch(err => {
  console.error('Error deleting webhook: ', err);
});

telegram.deleteMyCommands().then(() => {
  console.log('Commands deleted successfully');
});
