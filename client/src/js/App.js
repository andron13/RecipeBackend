import React, { Component } from "react";
import Input from "./components/view/Input.js";
import axios from "axios"

class FormContainer extends Component {
    constructor() {
        super();

        this.state = {
            seo_title: "SEO Title",
            cocktails: []
        };

        this.handleChange = this.handleChange.bind(this);
        this.handleClick = this.handleClick.bind(this);
    }

    handleChange(event) {
        this.setState({[event.target.id]: event.target.value});
    }


    handleClick(event) {
        event.preventDefault();

        axios.get("http://localhost:63342/Recipes").then(res => {
            const cocktails = res.data;
            this.setState({cocktails});
        })
    }


    render() {
        const { seo_title, cocktails } = this.state
        const listItems = cocktails.map((d) => <li key={d.name}>{d.name}</li>);
        return (
            <div>
                <form id="article-form">
                    <Input
                        text={seo_title}
                        label="seo title"
                        type="text"
                        id="seo_title"
                        value={seo_title}
                        handleChange={this.handleChange}
                    />
                </form>
                <button onClick={this.handleClick} type="submit">Get Cocktail</button>
                <ul style={listItems.length ? {} : { display: 'none' }}>{listItems}</ul>
            </div>
        )}
}

export default FormContainer;