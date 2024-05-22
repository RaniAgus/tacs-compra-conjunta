VOLUME_NAME="tacs-certs"

CONTAINER_PATH="/usr/share/elasticsearch/config/certs"

DESTINATION="${1:-./certs}"

CONTAINER_ID=$(docker run -d -v "${VOLUME_NAME}:${CONTAINER_PATH}" alpine tail -f /dev/null)

mkdir -p "${DESTINATION}"

docker cp "${CONTAINER_ID}:${CONTAINER_PATH}/es01/es01.crt" "${DESTINATION}/ca.crt"

docker rm -f "${CONTAINER_ID}" > /dev/null
