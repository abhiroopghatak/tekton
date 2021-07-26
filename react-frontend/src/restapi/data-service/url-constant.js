import axios from 'axios';

const {
    DB_CONFIG_DATA_SERVICE_PORT,
    DB_CONFIG_DATA_SERVICE_URL,
    CLUSTER_LIST_API,
} = process.env

export const clusterListServiceAPI = axios.create({
    baseURL: DB_CONFIG_DATA_SERVICE_URL || 'http://localhost:9000'
})


