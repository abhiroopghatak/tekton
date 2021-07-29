import React from 'react';
import { Container, Button, Modal, ModalBody, ModalHeader, ModalFooter } from 'mdbreact';
class ClusterModal extends React.Component {
	constructor(props) {
		super(props);
		this.state = {
			modal: false
		};
	}
	toggle = () => {
		this.setState({
			modal: false
		});
	}
	render() {
		return (
<Container>
<Button color="danger"onClick={this.toggle}>Modal</Button>
<Modal isOpen={this.props.modal}toggle={this.props.toggle}>
<ModalHeader toggle={this.toggle}>{this.props.ModalHeader}</ModalHeader>
<ModalBody>
				(...)
</ModalBody>
<ModalFooter>
<Button color="secondary"onClick={this.toggle}>Close</Button>{ ' ' }
		<Button color="primary" > Save changes</Button >
</ModalFooter >
</Modal >
</Container >
);
	}
}
export default ClusterModal;