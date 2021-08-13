import React from "react";
import { slide as Menu } from "react-burger-menu";
import '../../styles/componentstyles/Sidebar.css';


export default props => {
  return (
    <Menu {...props}>
      <a className="menu-item" href="/home">
        Home
      </a>

      <a className="menu-item" href="/about">
        About
      </a>

      <a className="menu-item" href="/access">
        Cluster Access request
      </a>

      <a className="menu-item" href="/contact">
        Contact us
      </a>
    </Menu>
  );
};