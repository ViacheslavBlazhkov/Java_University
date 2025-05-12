import React, {useState} from 'react';
import {View, FlatList, Text, StyleSheet} from 'react-native';
import {Input} from 'react-native-elements';

const SearchComponent = () => {
	const initialData = [
		{id: 1, name: 'Елемент 1'},
		{id: 2, name: 'Елемент 2'},
		{id: 3, name: 'Елемент 3'},
	];

	const [query, setQuery] = useState('');
	const [data, setData] = useState(initialData);

	const handleSearch = (text) => {
		const filteredData = initialData.filter(item =>
			item.name.toLowerCase().includes(text.toLowerCase())
		);
		setData(filteredData);
		setQuery(text);
	};

	return (
		<View>
			<Input
				placeholder="Введіть запит"
				value={query}
				onChangeText={handleSearch}
			/>
			<FlatList
				data={data}
				keyExtractor={item => item.id.toString()}
				renderItem={({item}) => <Text style={styles.item}>{item.name}</Text>}
			/>
		</View>
	);
};

const styles = StyleSheet.create({
	container: {
		flex: 1,
		backgroundColor: '#FFFFFF',
		paddingTop: 15,
		paddingHorizontal: 15,
	},
	item: {
		textAlign: 'center',
		marginTop: 10,
		padding: 15,
		fontSize: 20,
		backgroundColor: 'steelblue',
	}
});

export default SearchComponent;