import axios from 'axios'

import clusterListServiceAPI from './url-constant';


const ClUSTER_LIST_API_URL = 'http://localhost:9000/api/clusters/getAll';

class ClusterDataService {

    getAllClusters(){
        return axios.get(ClUSTER_LIST_API_URL);
    }
}

export default new ClusterDataService();