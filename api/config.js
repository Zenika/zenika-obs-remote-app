// config.js
const env = process.env.NODE_ENV; // 'dev' or 'test'

const dev = {
    app: {
        host: process.env.DEV_APP_HOST || 'localhost',
        port: parseInt(process.env.DEV_APP_PORT) || 3000
    },
    /*db: {
        host: process.env.DEV_DB_HOST || 'localhost',
        port: parseInt(process.env.DEV_DB_PORT) || 27017,
        name: process.env.DEV_DB_NAME || 'db'
    },*/
    obs: {
        host: process.env.DEV_OBS_HOST || 'localhost',
        port: parseInt(process.env.DEV_OBS_PORT) || 4444,
        pwd:  process.env.DEV_OBS_PWD || '$up3rSecretP@ssw0rd'
    }
};
const test = {
    app: {
        host: process.env.TEST_APP_HOST || 'localhost',
        port: parseInt(process.env.TEST_APP_PORT) || 3000
    },
    obs: {
        host: process.env.TEST_OBS_HOST || 'localhost',
        port: parseInt(process.env.TEST_OBS_PORT) || 4444,
        pwd:  process.env.TEST_OBS_PWD || '$up3rSecretP@ssw0rd'
    }
};
const prod = {
    app: {
        host: process.env.PROD_APP_HOST || 'localhost',
        port: parseInt(process.env.PROD_APP_PORT) || 3000
    },
    obs: {
        host: process.env.PROD_OBS_HOST || 'localhost',
        port: parseInt(process.env.PROD_OBS_PORT) || 4444,
        pwd:  process.env.PROD_OBS_PWD || '$up3rSecretP@ssw0rd'
    }
};;

const config = {
    dev,
    test,
    prod
};

module.exports = config[env];