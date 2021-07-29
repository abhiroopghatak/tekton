import axios from 'axios'

import clusterListServiceAPI from './url-constant';



const BASE_URL = 'http://localhost:9000';
class ClusterDataService {

	getAllClusters(){
	return axios.get( BASE_URL+'/api/clusters/getAll');
}


getClusterMetadata(data){
	return axios.post(BASE_URL+'/api/clusters/clusterSummary', data);
}
}

export default new ClusterDataService();