import React, {useState} from 'react';
import {StyleSheet, Text, View, TextInput, Button, Alert} from 'react-native';
import {addClient} from "@/scripts/clientsApi";

export default function AddClientForm({clientAdded}) {
	const [name, setName] = useState('');
	const [email, setEmail] = useState('');

	const handleAddClient = async () => {
		const client = {
			name,
			email,
		};
		await addClient(client);
		clientAdded();
		setName('');
		setEmail('');

	};

	return (
		<View style={styles.container}>
			<Text style={styles.title}>Add Client</Text>
			<TextInput
				style={styles.input}
				value={name}
				onChangeText={text => setName(text)}
				placeholder="Name"
			/>
			<TextInput
				style={styles.input}
				value={email}
				onChangeText={text => setEmail(text)}
				placeholder="Email"
				keyboardType="email-address"
			/>
			<Button title="Add Client" onPress={handleAddClient}/>
		</View>
	);
}

const styles = StyleSheet.create({
	container: {
		flex:            1,
		justifyContent:  'center',
		alignItems:      'center',
		padding:         20,
		backgroundColor: 'white',
	},
	title:     {
		fontSize:     20,
		fontWeight:   'bold',
		marginBottom: 20,
	},
	input:     {
		width:        '100%',
		height:       40,
		borderWidth:  1,
		borderColor:  'gray',
		marginBottom: 10,
		paddingLeft:  10,
	},
});