# Compra Conjunta Telegram Integrat

## Crear bot de telegram

1. Buscar a `@BotFather` en telegram.
2. Crear un nuevo bot con el comando `/newbot`.
3. Guardar el token que te da `@BotFather`.

## Instalar dependencias

```sh
pnpm i -g serverless
pnpm i
```

## Inicializar serverless

Para iniciar sesión en Serverless Framework, se debe ejecutar por única vez:

```sh
sls
```

Debemos utilizar una cuenta que pertenezca a la organización de Serverless
Framework que aparece en el archivo `serverless.yml`.

## Desplegar

Las credenciales del webhook ya se encuentran guardadas en AWS SSM, por lo que
no es necesario hacer ninguna configuración adicional, alcanza con hacer:

```sh
sls deploy
```

Este paso se puede repetir cuantas veces se necesite para actualizar el código
del webhook con nuestros cambios.

## Configurar webhook

Este paso solo es necesario la primera vez que se configura el webhook, ya que
debemos indicarle a Telegram la URL del mismo.

```sh
npm run set:webhook "$(sls info --verbose 2> /dev/null | grep POST | awk '{print $4}')"
```

## Chequear logs

Si queremos ver los logs del webhook, podemos hacerlo con el siguiente comando:

```sh
sls logs -f webhook
```
