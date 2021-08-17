import axios from 'axios'
import authHeader from '../../components/common/auth-header';

const BASE_URL = 'http://localhost:9000';
class DataService {

	getAllClusters() {
		return axios.get(BASE_URL + '/api/clusters/getAll',{ headers: authHeader() });
	}


	getClusterMetadata(data) {
		return axios.post(BASE_URL + '/api/clusters/clusterSummary', data , { headers: authHeader() });
	}
	
	loginUser(data){
		return axios.post(BASE_URL + '/api/authenticate', data);
	}
	
	registerUser(data){
		return axios.post(BASE_URL + '/api/users/register', data);
	}
	getUserByEmail(data) {
		return axios.get(BASE_URL + '/api/users/getOne/' + data , { headers: authHeader() });
	}

	raiseClusterAccess(data) {
		return axios.post(BASE_URL + '/api/users/requestAccess', data, { headers: authHeader() });
	}
	
	getResourcePerNamespaces(data) {
		return axios.post(BASE_URL + '/api/clusters/platform/label/namespaces/resources', data,{ headers: authHeader() });
	}
	
	getPodResourcePerNamespaces(data) {
		return axios.post(BASE_URL + '/api/clusters/platform/namespaces/pods', data,{ headers: authHeader() });
	}

	getCostDataPerCluster(data) {
		return axios.get(BASE_URL + '/api/clusters/getcosts/' + data , { headers: authHeader() });
	}
	

}

export default new DataService();