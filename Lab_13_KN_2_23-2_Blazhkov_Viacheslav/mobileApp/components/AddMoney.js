import React, {useState} from "react";
import {StyleSheet, Text, View, TextInput, Button} from "react-native";
import {depositMoney} from "@/scripts/clientsApi";

export default function AddMoneyForm({navigation, clientId, onAdded}) {
	const [amount, setAmount] = useState('');
	const [day, setDay] = useState('');
	const [month, setMonth] = useState('');
	const [year, setYear] = useState('');

	const handleAddMoney = async () => {
		try {
			const depositRequest = {
				amount: parseInt(amount),
				day:    parseInt(day),
				month:  parseInt(month),
				year:   parseInt(year),
			};

			await depositMoney(clientId, depositRequest);

			setAmount('');
			setDay('');
			setMonth('');
			setYear('');

			onAdded();
		} catch (e) {
			console.log(e)
		}
	}
	return (
		<View style={styles.container}>
			<Text style={styles.title}>Add Money to Client</Text>
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
			<Button title="Add Money" onPress={handleAddMoney}/>
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