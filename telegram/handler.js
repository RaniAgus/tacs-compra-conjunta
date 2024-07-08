const bot = require('./bot');

module.exports.webhook = async (event, _context, _callback) => {
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
  } catch (err) {
    console.error('Error handling update:', err);
  }

  return {
    statusCode: 200,
    body: JSON.stringify({
      message: 'Update handled',
    })
  };
};
