FROM public.ecr.aws/lambda/nodejs:20

COPY package.json package-lock.json ./

RUN npm install

COPY handler.js ./

CMD [ "handler.webhook" ]
