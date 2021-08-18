export default function authHeader() {
	const token = JSON.parse(localStorage.getItem('_token'));

	if (token) {
		return { Authorization: 'Bearer ' + token };
	} else {
		return {};
	}
}