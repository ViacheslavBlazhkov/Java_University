import React, {useState} from 'react';
import {View, FlatList, Text, StyleSheet} from 'react-native';
import {Input, Button} from 'react-native-elements';

const App = () => {
	const [newItem, setNewItem] = useState('');

	const [data, setData] = useState([
		{id: '1', title: 'Елемент 1'},
		{id: '2', title: 'Елемент 2'},
		{id: '3', title: 'Елемент 3'},
		{id: '4', title: 'Елемент 4'},
	]);

	const addNewItem = () => {
		setData([...data, {id: (data.length + 1).toString(), title: newItem}]);
	};

	return (
		<View style={styles.container}>
			<FlatList
				data={data}
				renderItem={({item}) => <Text style={styles.item}>{item.title}</Text>}
				keyExtractor={(item) => item.id}
			/>
			<Input
				id="new-element-input"
				label="Новий елемент"
				value={newItem}
				onChangeText={setNewItem}
			/>
			<Button
				title="Додати"
				onPress={addNewItem}
			/>
		</View>
	);
}

const styles = StyleSheet.create({
	container: {
		flex: 1,
		backgroundColor: '#FFFFFF',
		paddingTop: 15,
		paddingHorizontal: 15,
	},
	item: {
		textAlign: 'center',
		marginTop: 20,
		padding: 25,
		fontSize: 30,
		backgroundColor: 'steelblue',
	}
});

export default App;