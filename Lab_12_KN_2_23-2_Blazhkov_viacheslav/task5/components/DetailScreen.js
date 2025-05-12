import React from 'react';
import {View, Text, Image} from 'react-native';

const DetailScreen = ({route}) => {
	const {item} = route.params;

	return (
		<View style={{backgroundColor: 'white', flex: 1, alignItems: 'center', justifyContent: 'center'}}>
			<Image source={{uri: item.image}} style={{width: 200, height: 200}}/>
			<Text>{item.name}</Text>
			<Text>{item.description}</Text>
		</View>
	);
};

export default DetailScreen;