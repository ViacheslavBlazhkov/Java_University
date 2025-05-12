import React from 'react';
import {createStackNavigator} from "@react-navigation/stack";
import HomeScreen from './../../components/HomeScreen';
import ListScreen from './../../components/ListScreen';

const Stack = createStackNavigator();

const App = () => {
	return (
		<Stack.Navigator>
			<Stack.Screen name="Home" component={HomeScreen}/>
			<Stack.Screen name="FavoriteList" component={ListScreen}/>
			<Stack.Screen name="ChosenList" component={ListScreen}/>
		</Stack.Navigator>
	);
};

export default App;