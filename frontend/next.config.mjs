// @ts-check

import { PHASE_DEVELOPMENT_SERVER } from 'next/constants.js'

const config = (phase, { defaultConfig }) => {
    if (phase === PHASE_DEVELOPMENT_SERVER) {
        return {
            env: {
                BACKEND_URL: 'http://localhost:8080',
            },
        }
    }

    return {
        env: {
            BACKEND_URL: process.env.BACKEND_URL,
        },
        output: 'standalone',
    };
}

export default config
