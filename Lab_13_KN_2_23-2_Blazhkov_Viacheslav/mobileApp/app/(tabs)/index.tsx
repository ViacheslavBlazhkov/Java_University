import React from 'react';
import { StyleSheet } from 'react-native';
import { createStackNavigator } from '@react-navigation/stack';
import { HomeScreen } from '../../components/HomeScreen';
import { ClientScreen } from '../../components/ClientScreen';
import { ClientsScreen } from '../../components/ClientsScreen';

const Stack = createStackNavigator();

export default function App() {
	return (
			<Stack.Navigator>
				<Stack.Screen name="Home" component={HomeScreen} />
				<Stack.Screen name="Client" component={ClientScreen} />
				<Stack.Screen name="Clients" component={ClientsScreen} />
			</Stack.Navigator>
	)
}

const styles = StyleSheet.create({
	container: {
		flex: 1,
		justifyContent: 'center',
		alignItems: 'center',
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