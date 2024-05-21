// @ts-check

import { PHASE_DEVELOPMENT_SERVER } from 'next/constants.js'

export default (phase, { defaultConfig }) => {
    if (phase === PHASE_DEVELOPMENT_SERVER) {
        return {
            env: {
                BASE_URL: 'http://localhost:8080',
            },
        }
    }

    return {
        env: {
            BASE_URL: process.env.BASE_URL,
        },
        output: 'standalone',
    };
}
