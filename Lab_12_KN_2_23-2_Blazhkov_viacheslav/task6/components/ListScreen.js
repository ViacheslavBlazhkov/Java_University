import React from 'react';
import {View, Text} from 'react-native';

const ListScreen = ({route}) => {
	const {items, title} = route.params;
	return (
		<View style={{backgroundColor: 'white', flex: 1, justifyContent: 'center', alignItems: 'center'}}>
			<Text>{title}</Text>
			{items.map((item, index) => (
				<Text key={index}>{item}</Text>
			))}
		</View>
	);
};

export default ListScreen;