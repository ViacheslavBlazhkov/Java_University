task1();
task2();
task3();
task4();
task5();
task6();
task7();
task8();

function task1() {
	let myArray = [1, 2, 3, 4, 5];
	insertText('task-1-start', "Початковий масив: " + myArray);

	function doubleValue(value) {
		return value * 2;
	}

	let transformedArray = myArray.map(doubleValue);
	insertText('task-1-end', "Трансформований масив: " + transformedArray)
}

function task2() {
	let myArray = [4, 7, 1, 9, 3, 8, 5];
	insertText('task-2-start', "Початковий масив: " + myArray);
	let largestNumber = Math.max(...myArray);
	insertText('task-2-end', "Найбільше число: " + largestNumber);
}

function task3() {
	let myArray = [
		{name: 'John', age: 28},
		{name: 'Mary', age: 32},
		{name: 'David', age: 21},
		{name: 'Jane', age: 45},
	];
	insertText('task-3-start', "Початковий масив: " + JSON.stringify(myArray))

	function isOlderThan30(person) {
		return person.age > 30;
	}

	let filteredArray = myArray.filter(isOlderThan30);
	insertText('task-3-end', "Більше 30 років: " + JSON.stringify(filteredArray))
}

function task4() {
	let myArray1 = [10, 2, 3, 4, 5];
	let myArray2 = [4, 5, 10, 7, 8];
	insertText('task-4-start', "Масив 1: " + myArray1 + " Масив 2: " + myArray2)
	let combinedArray = myArray1.concat(myArray2);
	let uniqueArray = combinedArray.filter((item, index) => {
		return combinedArray.indexOf(item) === index;
	});
	insertText('task-4-end', "Об'єднаний масив: " + uniqueArray)
}

function task5() {
	let myArray = [
		{name: 'John', age: 28},
		{name: 'Mary', age: 32},
		{name: 'David', age: 21},
		{name: 'Jane', age: 45},
	];
	insertText('task-5-start', "Початковий масив: " + JSON.stringify(myArray))
	let sorted = myArray.sort((a, b) => a.age - b.age);
	insertText('task-5-end', "Відсортований масив: " + JSON.stringify(sorted))
}

function task6() {
	let myArray = [
		{name: 'John', age: 10},
		{name: 'Mary', age: 32},
		{name: 'David', age: 12},
		{name: 'Jane', age: 45},
	];
	insertText('task-6-start', "Початковий масив: " + JSON.stringify(myArray))
	let grouped = Object.groupBy(myArray, ({age}) => age > 20 ? 'adult' : 'young');
	insertText('task-6-end', "Групи масивів: " + JSON.stringify(grouped))
}

function task7() {
	let text = "text1 text2 word3  word4 word5";
	insertText('task-7-start', "Текст: " + text);
	let count = text.split(" ").length;
	insertText('task-7-end', "Кількість слів: " + count)
}

function task8() {
	let myArray = [
		{name: 'John', age: 10},
		{name: 'Mary', age: 32},
		{name: 'David', age: 12},
		{name: 'Jane', age: 45},
	];
	insertText('task-8-start', "Початковий масив: " + JSON.stringify(myArray))
	let average = myArray.reduce((total, person) => total + person.age, 0) / myArray.length;
	insertText('task-8-end', "Середній вік: " + average)
}

function insertText(id, text) {
	document.getElementById(id).innerText = text;
}