const { bot } = require('./scenes');

module.exports.webhook = async (event, context, callback) => {
  if (event.headers?.['X-Telegram-Bot-Api-Secret-Token'] !== process.env.TELEGRAM_WEBHOOK_SECRET) {
    return {
      statusCode: 403,
      body: JSON.stringify({
        error: 'Unauthorized',
      })
    };
  }

  let body;
  try {
    body = JSON.parse(event.body);
  } catch (err) {
    return {
      statusCode: 400,
      body: JSON.stringify({
        error: 'Invalid request body',
      })
    };
  }

  try {
    await bot.handleUpdate(body);
    return {
      statusCode: 200,
      body: JSON.stringify({
        message: 'Update handled',
      })
    };
  } catch (err) {
    console.error('Error handling update:', err);
    return {
      statusCode: 500,
      body: JSON.stringify({
        error: 'Error handling update',
      })
    };
  }
};
