import React from 'react';
import {View, Text, Image, StyleSheet} from 'react-native';

const StartScreen = () => {
	return (
		<View style={styles.container}>
			<Image source={require('../assets/images/cat.png')}/>
			<Text style={styles.welcomeText}>Ласкаво просимо!</Text>
		</View>
	);
};

const styles = StyleSheet.create({
	container:   {
		flex:            1,
		justifyContent:  'center',
		alignItems:      'center',
		backgroundColor: 'white',
	},
	logo:        {
		width:        200,
		height:       200,
		marginBottom: 20,
	},
	welcomeText: {
		fontSize:   24,
		fontWeight: 'bold',
		color:      'black',
	},
});

export default StartScreen;