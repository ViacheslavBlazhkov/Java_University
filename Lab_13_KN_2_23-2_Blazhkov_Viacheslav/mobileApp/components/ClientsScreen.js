import React, {Fragment, useEffect, useState} from "react";
import {StyleSheet, Text, ScrollView, TouchableOpacity, Button} from "react-native";
import {getClients, deleteClient} from "@/scripts/clientsApi";
import AddClientForm from "./AddClient";

export function ClientsScreen({navigation}) {
	const [items, setItems] = useState([]);

	useEffect(() => {
		fetchItems();
	}, []);

	const fetchItems = async () => {
		try {
			const response = await getClients();
			setItems(response.data);
		} catch (error) {
			console.log(error);
		}
	}

	const handleClientsPress = () => {
		navigation.navigate('Home');
	};
	const handleClientDetailPress = (id) => {
		navigation.navigate('Client', {clientId: id, clients: items})
	}
	const handleClientDeletePress = async (id) => {
		try {
			await deleteClient(id);
			navigation.navigate('Clients');

		} catch (error) {
			console.log(error);
		}
	}

	return (
		<ScrollView style={styles.container}>
			<AddClientForm clientAdded={fetchItems}/>
			{items.map((data) => (
				<Fragment key={data.id}>
					<hr style={{margin: 10}}/>
					<TouchableOpacity onPress={() => handleClientDetailPress(data.id)}>
						<Text style={styles.link}>Client {data.id}</Text>
					</TouchableOpacity>
					<Text style={styles.label}>Name:</Text>
					<Text style={styles.text}>{data.name}</Text>
					<Text style={styles.label}>Email:</Text>
					<Text style={styles.text}>{data.email}</Text>
					<Text style={styles.label}>User Money:</Text>
					<Text style={styles.text}>{data.clientMoney}</Text>
					<Button title="Delete" onPress={() => handleClientDeletePress(data.id)}></Button>
				</Fragment>
			))}
			<TouchableOpacity onPress={handleClientsPress}>
				<Text style={styles.link}>Повернутися до головної сторінки</Text>
			</TouchableOpacity>
		</ScrollView>
	)
}

const styles = StyleSheet.create({
	container: {
		flex:            1,
		padding:         20,
		backgroundColor: 'white',
	},
	title:     {
		fontSize:     20,
		fontWeight:   "bold",
		marginBottom: 10,
	},
	link:      {
		fontSize:           16,
		color:              "blue",
		textDecorationLine: "underline",
	},
	label:     {
		fontSize:   16,
		fontWeight: "semibold",
	},
});