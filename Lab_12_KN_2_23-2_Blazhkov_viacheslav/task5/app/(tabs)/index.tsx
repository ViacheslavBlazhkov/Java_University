import React from 'react';
import {createStackNavigator} from '@react-navigation/stack';
import HomeScreen from './../../components/HomeScreen';
import DetailScreen from '../../components/DetailScreen';

const Stack = createStackNavigator();

const App = () => {
	return (
		<Stack.Navigator>
			<Stack.Screen name="Home" component={HomeScreen}/>
			<Stack.Screen name="Detail" component={DetailScreen}/>
		</Stack.Navigator>
	);
};

export default App;