import axios from 'axios'

import clusterListServiceAPI from './url-constant';



const BASE_URL = 'http://localhost:9000';
class DataService {

	getAllClusters() {
		return axios.get(BASE_URL + '/api/clusters/getAll');
	}


	getClusterMetadata(data) {
		return axios.post(BASE_URL + '/api/clusters/clusterSummary', data);
	}

	getCostDataPerCluster(data) {
		return axios.get(BASE_URL + '/api/clusters/getcosts/' + data);
	}
	getUserByEmail(data) {
		return axios.get(BASE_URL + '/api/users/getOne/' + data);
	}


}

export default new DataService();