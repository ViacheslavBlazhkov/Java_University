import React, {useState} from 'react';
import {View, StyleSheet} from 'react-native';
import {Input, Button} from 'react-native-elements';

const App = () => {
	const [email, setEmail] = useState('');
	const [password, setPassword] = useState('');

	const handleLogin = () => {
		if (email && password) {
			console.log('Успішний вхід!');
		} else {
			console.log('Помилка входу!');
		}
	};

	return (
		<View style={styles.container}>
			<Input
				label="Email"
				value={email}
				onChangeText={setEmail}
			/>
			<Input
				label="Password"
				value={password}
				onChangeText={setPassword}
			/>
			<Button
				title="Вхід"
				onPress={handleLogin}
			/>
		</View>
	);
};

const styles = StyleSheet.create({
	container: {
		flex: 1,
		justifyContent: 'center',
		padding: 16,
		backgroundColor: 'white',
	},
});

export default App;