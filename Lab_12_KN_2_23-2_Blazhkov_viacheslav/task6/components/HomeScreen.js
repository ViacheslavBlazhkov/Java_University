import React, {useState} from 'react';
import {View, Text, Button, TouchableOpacity} from 'react-native';
import {Input} from 'react-native-elements';

const HomeScreen = ({navigation}) => {
	const [favoriteList, setFavoriteList] = useState([]);
	const [chosenList, setChosenList] = useState([]);
	const [itemText, setItemText] = useState('');

	const handleAddToFavorite = () => {
		setFavoriteList([...favoriteList, itemText]);
		setItemText('');
	};
	const handleAddToChosen = () => {
		setChosenList([...chosenList, itemText]);
		setItemText('');
	};

	const handleGoToFavoriteList = () => {
		navigation.navigate('FavoriteList', {items: favoriteList, title: "Улюблений список"});
	};
	const handleGoToChosenList = () => {
		navigation.navigate('ChosenList', {items: chosenList, title: "Обраний список"});
	};

	return (
		<View style={{backgroundColor: 'white', flex: 1, gap: 16, justifyContent: 'center', alignItems: 'center'}}>
			<Text>Домашній екран</Text>
			<Input
				id="input"
				style={{borderStyle: 'solid', borderWidth: 1, borderColor: 'steelblue'}}
				value={itemText}
				placeholder="Введіть елемент"
				onChangeText={setItemText}
			/>
			<View style={{flexDirection: 'row', gap: 16}}>
				<TouchableOpacity style={{backgroundColor: 'red', padding: 10}} onPress={handleAddToChosen}>
					<Text style={{color: 'white'}}>Додати до обраного</Text>
				</TouchableOpacity>
				<Button title="Додати до улюбленого" onPress={handleAddToFavorite}/>
			</View>
			<View style={{flexDirection: 'row', gap: 16}}>
				<TouchableOpacity style={{backgroundColor: 'red', padding: 10}} onPress={handleGoToChosenList}>
					<Text style={{color: 'white'}}>Перейти до обраного списку</Text>
				</TouchableOpacity>
				<Button title="Перейти до улюбленого списку" onPress={handleGoToFavoriteList}/>
			</View>
		</View>
	);
};

export default HomeScreen;