import React, { Component } from 'react'

class HeaderComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
                 
        }
    }

    render() {
        return (
            <div className="page-top">
                <header>
                    <nav className="navbar navbar-expand-md navbar-dark bg-dark">
                    <div><a href="https://www.hcl.com/" className="navbar-brand">K8s Cluster Management App</a></div>
                    </nav>
                </header>
            </div>
        )
    }
}

export default HeaderComponent