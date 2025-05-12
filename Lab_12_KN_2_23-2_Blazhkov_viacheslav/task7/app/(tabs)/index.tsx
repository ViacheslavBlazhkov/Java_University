import React from 'react';
import {View} from 'react-native';
import SearchComponent from "@/components/SearchComponent";

const HomeScreen = () => {
	return (
		<View style={{backgroundColor: 'white', flex: 1, gap: 16, justifyContent: 'center', alignItems: 'center'}}>
			<SearchComponent/>
		</View>
	);
};

export default HomeScreen;