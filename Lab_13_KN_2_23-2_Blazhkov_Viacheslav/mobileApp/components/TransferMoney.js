import React, {useState} from "react";
import {StyleSheet, Text, View, TextInput, Button} from "react-native";
import {transferMoney} from "../scripts/clientsApi";
import RNPickerSelect from 'react-native-picker-select';


export default function TransferMoneyForm({navigation, fromClientId, clients, onAdded}) {
	const clientOptions = clients.map(client => ({label: client.name, value: client.id}));
	const [toClientId, setToClientId] = useState(-1);
	const [amount, setAmount] = useState('');
	const [day, setDay] = useState('');
	const [month, setMonth] = useState('');
	const [year, setYear] = useState('');

	const handleAddMoney = async () => {
		try {
			const transferRequest = {
				fromClientId: fromClientId,
				toClientId:   toClientId,
				amount:       parseInt(amount),
				day:          parseInt(day),
				month:        parseInt(month),
				year:         parseInt(year),
			};

			await transferMoney(transferRequest);

			setToClientId(-1);
			setAmount('');
			setDay('');
			setMonth('');
			setYear('');

			onAdded();
		} catch (e) {
			alert(e.response.data);
		}
	}
	return (
		<View style={styles.container}>
			<Text style={styles.title}>Transfer Money to Client</Text>
			<RNPickerSelect
				onValueChange={(value) => setToClientId(value)}
				items={clientOptions}
				useNativeAndroidPickerStyle={false}
				style={styles.input}
				placeholder={{ label: 'Select a client', value: null }}
			/>
			<br/>
			<TextInput
				style={styles.input}
				value={amount}
				onChangeText={(text) => setAmount(text)}
				placeholder="Amount"
				keyboardType="numeric"
			/>
			<TextInput
				style={styles.input}
				value={day}
				onChangeText={(text) => setDay(text)}
				placeholder="Day"
				keyboardType="numeric"
			/>
			<TextInput
				style={styles.input}
				value={month}
				onChangeText={(text) => setMonth(text)}
				placeholder="Month"
				keyboardType="numeric"
			/>
			<TextInput
				style={styles.input}
				value={year}
				onChangeText={(text) => setYear(text)}
				placeholder="Year"
				keyboardType="numeric"
			/>
			<Button title="Transfer Money" onPress={handleAddMoney}/>
		</View>
	);
}
const styles = StyleSheet.create({
	container: {
		minWidth:        "300px",
		flex:            1,
		justifyContent:  "center",
		alignItems:      "center",
		padding:         20,
		backgroundColor: "white",
	},
	title:     {
		fontSize:     20,
		fontWeight:   "bold",
		marginBottom: 20,
	},
	input:     {
		width:        "100%",
		height:       40,
		borderWidth:  1,
		borderColor:  "gray",
		marginBottom: 10,
		paddingLeft:  10,
	},
});