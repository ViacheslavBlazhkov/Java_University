import axios from "axios";

const api = axios.create({
	baseURL: "http://localhost:8080",
});

export const getClients = () => api.get("/clients");
export const getClient = (id) => api.get("/clients/" + id);
export const getTransactionsByDates = (id, fromDate, toDate) => api.get(`/clients/${id}/transactions_dates?fromDate=${fromDate}&toDate=${toDate}`);
export const addClient = (data) => api.post("/clients", data);
export const depositMoney = (id, data) => api.post(`/clients/${id}/transactions`, data);
export const withdrawMoney = (id, data) => api.post(`/clients/${id}/withdraw`, data);
export const transferMoney = (data) => api.post(`/clients/transfer`, data);
export const deleteClient = (id) => api.delete("/clients/" + id);

const apis = {
	getClients,
	getClient,
	getTransactionsByDates,
	addClient,
	depositMoney,
	withdrawMoney,
	transferMoney,
	deleteClient,
};

export default apis;