import { useState, useEffect } from 'react';

function App() {
  const [todos, setTodos] = useState([]);
  const [input, setInput] = useState('');

  const handleAddTodo = () => {
    // If there is no input or same todo already exists
    if(input === '' || todos.some(todo => todo.title === input)){
      alert('Please enter a different todo');
      setInput('');
      return;
    }
    if (input) {
      const newTodo = { title: input, completed: false };
      setTodos([...todos, newTodo]);
      setInput('');
      fetch(`/api/todoEntity/${newTodo.title}`, {
        method: 'POST',
        headers: {
          'Content-Type': 'text/plain'
        },
        // This is currently not needed
        // body: JSON.stringify(newTodo)
      })
        // .then(res => res.json())
        .then(data => console.log(data))
        .catch(err => console.error(err));
    }
  };

  const handleDeleteTodo = (title) => {
    setTodos(todos.filter(todo => todo.title !== title));
    fetch(`/api/todoEntity/${title}/delete`, {
      method: 'DELETE',
      headers: {
        'Content-Type': 'text/plain'
      },
    })
    .then(res => {
      if (!res.ok) {
        throw new Error(`Server error: ${res.status}`);
      }
      return res;
    })
    .then(data => console.log(data))
    .catch(err => console.error(err));
  };

  const handleToggleCompleted = (title) => {
    setTodos(todos.map(todo => {
      if (todo.title === title) {
        const updatedTodo = { ...todo, completed: !todo.completed };
        fetch(`/api/todoEntity/${title}/update`, {
          method: 'PUT',
          headers: {
            'Content-Type': 'text/plain'
          },
          // This is not needed
          // body: JSON.stringify({ completed: updatedTodo.completed })
        })
        .then(res => {
          if (!res.ok) {
            throw new Error(`Server error: ${res.status}`);
          }
          return res;
        })
        .then(data => console.log(data))
        .catch(err => console.error(err));
        return updatedTodo;
      } else {
        return todo;
      }
    }));
  };

  useEffect(() => {
    fetch('/api/todoEntity/titles')
      .then(res => {
        if (!res.ok) {
          throw new Error(`Server error: ${res.status}`);
        }
        return res.json();
      })
      .then(data => {
        setTodos(data)
      })
      .catch(err => console.error(err));
  }, []);
  return (
    <div>
      <input 
        value={input} 
        onChange={e => setInput(e.target.value)} 
        placeholder="New todo" 
      />
      <button onClick={handleAddTodo}>Add</button>
      <ul>
        {todos.map((todo, index) => (
          <li key={index}>
            {todo.title} 
            <button onClick={() => handleToggleCompleted(todo.title)}>
              {todo.completed ? 'Unmark as completed' : 'Mark as completed'}
            </button>
            <button onClick={() => handleDeleteTodo(todo.title)}>Delete</button>
          </li>
        ))}
      </ul>
    </div>
  );
}

export default App;