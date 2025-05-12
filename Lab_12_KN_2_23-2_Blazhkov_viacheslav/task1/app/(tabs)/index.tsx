import React from 'react';
import {NavigationContainer} from '@react-navigation/native';
import {createStackNavigator} from '@react-navigation/stack';
import StartScreen from './../../components/StartScreen';

const Stack = createStackNavigator();

const App = () => {
	return (
		<Stack.Navigator>
			<Stack.Screen
				name="Start"
				component={StartScreen}
				options={{headerShown: false}}
			/>
		</Stack.Navigator>
	);
};

export default App;