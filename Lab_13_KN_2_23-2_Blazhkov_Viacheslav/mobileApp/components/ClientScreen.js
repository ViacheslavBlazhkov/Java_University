import React, {Fragment, useEffect, useState, useCallback} from "react";
import {StyleSheet, Text, ScrollView, View, TouchableOpacity, TextInput, Button} from "react-native";
import {getClient, getTransactionsByDates} from "@/scripts/clientsApi";
import AddMoney from "./AddMoney";
import WithdrawMoney from "./WithdrawMoney";
import TransferMoney from "./TransferMoney";

const today = new Date();
const defaultFromDate = `${today.getFullYear()}-${String(today.getMonth() + 1).padStart(2, '0')}-01`;
const defaultToDate = `${today.getFullYear()}-${String(today.getMonth() + 1).padStart(2, '0')}-${String(today.getDate()).padStart(2, '0')}`;

export function ClientScreen({navigation, route}) {
	const [client, setClient] = useState({});
	const [transactionsByDates, setTransactionsByDates] = useState([]);
	const [fromDate, setFromDate] = useState(defaultFromDate);
	const [toDate, setToDate] = useState(defaultToDate);
	const clientId = route.params.clientId;
	const clients = route.params.clients;

	const fetchClient = useCallback(async () => {
		try {
			const response = await getClient(clientId);
			setClient(response.data);
		} catch (error) {
			console.log(error);
		}
	}, [clientId]);

	const fetchTransactionsByDates = useCallback(async () => {
		try {
			const response = await getTransactionsByDates(clientId, fromDate, toDate);
			setTransactionsByDates(response.data); // Оновлення стану транзакцій
		} catch (error) {
			console.log(error);
		}
	}, [clientId, fromDate, toDate]);

	useEffect(() => {
		fetchClient();
	}, [fetchClient]);

	const handleClientsPress = () => {
		navigation.navigate('Clients');
	};

	const formattedDate = (transaction) => `${transaction.transactionDate.day}/${transaction.transactionDate.month}/${transaction.transactionDate.year}`;

	return (
		<ScrollView style={styles.container}>
			<View style={{flexWrap: "wrap", flexDirection: "row", justifyContent: "space-between"}}>
				<AddMoney
					navigation={navigation}
					clientId={clientId}
					onAdded={fetchClient}
				/>
				<WithdrawMoney
					navigation={navigation}
					clientId={clientId}
					onAdded={fetchClient}
				/>
				<TransferMoney
					navigation={navigation}
					fromClientId={clientId}
					clients={clients}
					onAdded={fetchClient}
				/>
			</View>

			<Text style={styles.title}>Client {client.id}</Text>
			<Text style={styles.label}>Name:</Text>
			<Text style={styles.text}>{client.name}</Text>
			<Text style={styles.label}>Email:</Text>
			<Text style={styles.text}>{client.email}</Text>
			<Text style={styles.label}>User Money:</Text>
			<Text style={styles.text}>{client.clientMoney}</Text>

			<View style={{marginVertical: 10}}>
				<TextInput
					style={styles.input}
					value={fromDate}
					onChangeText={setFromDate}
					placeholder="From Date (YYYY-MM-DD)"
				/>
				<TextInput
					style={styles.input}
					value={toDate}
					onChangeText={setToDate}
					placeholder="To Date (YYYY-MM-DD)"
				/>
				<Button title="Fetch Transactions" onPress={fetchTransactionsByDates}/>
			</View>

			<View style={{flexWrap: "wrap", flexDirection: "row", justifyContent: "space-between"}}>
				<View style={{flex: 1}}>
					<Text style={styles.subtitle}>All Transactions</Text>
					{(client.transactions || []).map((transaction) => (
						<Fragment key={formattedDate(transaction) + transaction.amount}>
							<hr style={{margin: 10}}/>
							<Text style={styles.label}>Type:</Text>
							<Text style={styles.text}>{transaction.type}</Text>
							<Text style={styles.label}>Amount:</Text>
							<Text style={styles.text}>{transaction.amount}</Text>
							<Text style={styles.label}>Date:</Text>
							<Text style={styles.text}>{formattedDate(transaction)}</Text>
						</Fragment>
					))}
				</View>

				<View style={{flex: 1}}>
					<Text style={styles.subtitle}>Filtered Transactions</Text>
					{(transactionsByDates || []).map((transaction) => (
						<Fragment key={formattedDate(transaction) + transaction.amount}>
							<hr style={{margin: 10}}/>
							<Text style={styles.label}>Type:</Text>
							<Text style={styles.text}>{transaction.type}</Text>
							<Text style={styles.label}>Amount:</Text>
							<Text style={styles.text}>{transaction.amount}</Text>
							<Text style={styles.label}>Date:</Text>
							<Text style={styles.text}>{formattedDate(transaction)}</Text>
						</Fragment>
					))}
				</View>
			</View>

			<TouchableOpacity onPress={handleClientsPress}>
				<Text style={styles.link}>Повернутися до Клієнтів</Text>
			</TouchableOpacity>
		</ScrollView>
	);
}

const styles = StyleSheet.create({
	container: {
		backgroundColor: "white",
		padding:         20,
	},
	title:     {
		fontSize:     20,
		fontWeight:   "bold",
		marginBottom: 10,
	},
	label:     {
		fontWeight: "bold",
		marginTop:  5,
	},
	text:      {
		marginBottom: 5,
	},
	subtitle:  {
		fontSize:           18,
		fontWeight:         "bold",
		marginBottom:       10,
		textDecorationLine: "underline",
	},
	input:     {
		height:         40,
		borderColor:    "gray",
		borderWidth:    1,
		padding:        10,
		marginVertical: 5,
	},
	link:      {
		fontSize:           16,
		color:              "blue",
		textDecorationLine: "underline",
	},
});