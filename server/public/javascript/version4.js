console.log("running version4");


class Hello extends React.Component {
    render(){
        return React.createElement('div', null, `Hello ${this.props.toWhat}`);
    }
}
ReactDOM.render(
    React.createElement(Hello, {toWhat : 'World'}, null), 
    document.getElementById('react-root') 
    );
