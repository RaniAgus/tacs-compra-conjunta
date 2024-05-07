/** @type {import('next').NextConfig} */
const nextConfig = {
    env: {
        BASE_URL: process.env.BASE_URL,
    },
    output: 'standalone',
};

export default nextConfig;
