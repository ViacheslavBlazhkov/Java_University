import React from 'react';
import {createBottomTabNavigator} from '@react-navigation/bottom-tabs';
import {Text, View} from 'react-native';

function HomeScreen() {
	return (
		<View style={{flex: 1, justifyContent: 'center', alignItems: 'center', backgroundColor: 'white'}}>
			<Text>Home Screen</Text>
		</View>
	);
}

function ProfileScreen() {
	return (
		<View style={{flex: 1, justifyContent: 'center', alignItems: 'center', backgroundColor: 'white'}}>
			<Text>Profile Screen</Text>
		</View>
	)
}

const Tab = createBottomTabNavigator();

export default function App() {
	return (
		<Tab.Navigator>
			<Tab.Screen name="Home" component={HomeScreen}/>
			<Tab.Screen name="Profile" component={ProfileScreen}/>
		</Tab.Navigator>
	);
}

