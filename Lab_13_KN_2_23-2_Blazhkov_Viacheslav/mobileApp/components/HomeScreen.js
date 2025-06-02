import React from 'react';
import { StyleSheet, Text, View, TouchableOpacity } from 'react-native';

export function HomeScreen({ navigation }) {
	const handleClientsPress = () => {
		navigation.navigate('Clients');
	};

	return (
		<View style={styles.container}>
			<Text style={styles.title}>Головна сторінка</Text>
			<TouchableOpacity onPress={handleClientsPress}>
				<Text style={styles.link}>Перейти до клієнтів</Text>
			</TouchableOpacity>
		</View>
	);
}

const styles = StyleSheet.create({
	container: {
		flex: 1,
		alignItems: 'center',
		justifyContent: 'center',
		padding: 20,
	},
	title: {
		fontSize: 20,
		fontWeight: 'bold',
		marginBottom: 10,
	},
	link: {
		fontSize: 16,
		color: "blue",
		textDecorationLine: "underline",
	}
});