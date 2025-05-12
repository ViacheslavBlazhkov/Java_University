import React from 'react';
import {View, Text, TouchableOpacity} from 'react-native';
import {useNavigation} from '@react-navigation/native';

const HomeScreen = () => {
	const navigation = useNavigation();

	const items = [
		{id: 1, name: "Елемент 1", description: "Опис 1", image: "https://i.natgeofe.com/n/548467d8-c5f1-4551-9f58-6817a8d2c45e/NationalGeographic_2572187_16x9.jpg?w=1200"},
		{id: 2, name: "Елемент 2", description: "Опис 2", image: "https://upload.wikimedia.org/wikipedia/commons/4/4d/Cat_November_2010-1a.jpg"},
	]

	const navigateToDetailScreen = (item) => {
		navigation.navigate('Detail', {item});
	};

	return (
		<View style={{backgroundColor: 'white', flex: 1, alignItems: 'center', justifyContent: 'center'}}>
			{items.map((item) => (
				<TouchableOpacity key={item.id} onPress={() => navigateToDetailScreen(item)}>
					<Text>{item.name}</Text>
				</TouchableOpacity>
			))}
		</View>
	);
};

export default HomeScreen;