/**
 * Created by hkkang on 2016-04-07.
 */

(function () {
    //
    var initialize = function () {
        //
        console.log('nara-router initialize');
        navigated();
        window.addEventListener('hashchange', navigated);
    };

    var navigated = function () {
        //
        var hashLocation = window.location.hash;
        console.log('nara-router navigated -> ' + hashLocation);

        window.history.pushState(hashLocation, hashLocation);



    };






    /*
     * Components
     */


    var ContactForm = React.createClass({
        propTypes: {
            value: React.PropTypes.object.isRequired,
            onChange: React.PropTypes.func.isRequired,
            onSubmit: React.PropTypes.func.isRequired,
        },

        onNameChange: function(e) {
            this.props.onChange(Object.assign({}, this.props.value, {name: e.target.value}));
        },

        onEmailChange: function(e) {
            this.props.onChange(Object.assign({}, this.props.value, {email: e.target.value}));
        },

        onDescriptionChange: function(e) {
            this.props.onChange(Object.assign({}, this.props.value, {description: e.target.value}));
        },

        onSubmit: function(e) {
            e.preventDefault();
            this.props.onSubmit();
        },

        render: function() {
            var errors = this.props.value.errors || {};

            return (
                React.createElement('form', {onSubmit: this.onSubmit, className: 'ContactForm', noValidate: true},
                    React.createElement('input', {
                        type: 'text',
                        className: errors.name && 'ContactForm-error',
                        placeholder: 'Name (required)',
                        value: this.props.value.name,
                        onChange: this.onNameChange,
                    }),
                    React.createElement('input', {
                        type: 'email',
                        className: errors.email && 'ContactForm-error',
                        placeholder: 'Email (required)',
                        value: this.props.value.email,
                        onChange: this.onEmailChange,
                    }),
                    React.createElement('textarea', {
                        placeholder: 'Description',
                        value: this.props.value.description,
                        onChange: this.onDescriptionChange,
                    }),
                    React.createElement('button', {type: 'submit'}, "Add Contact")
                )
            );
        },
    });


    var ContactItem = React.createClass({
        propTypes: {
            name: React.PropTypes.string.isRequired,
            email: React.PropTypes.string.isRequired,
            description: React.PropTypes.string,
        },

        render: function() {
            return (
                React.createElement('li', {className: 'ContactItem'},
                    React.createElement('h2', {className: 'ContactItem-name'}, this.props.name),
                    React.createElement('a', {className: 'ContactItem-email', href: 'mailto:'+this.props.email}, this.props.email),
                    React.createElement('div', {className: 'ContactItem-description'}, this.props.description)
                )
            );
        },
    });


    var ContactsView = React.createClass({
        propTypes: {
            contacts: React.PropTypes.array.isRequired,
            newContact: React.PropTypes.object.isRequired,
            onChangeContact: React.PropTypes.func.isRequired,
            onSubmitContact: React.PropTypes.func.isRequired,
        },

        render: function() {
            var contactItemElements = this.props.contacts
                .filter(function(contact) { return contact.email; })
                .map(function(contact) { return React.createElement(ContactItem, contact); });

            return (
                React.createElement('div', {className: 'ContactView'},
                    React.createElement('h1', {className: 'ContactView-title'}, "Contacts"),
                    React.createElement('ul', {className: 'ContactView-list'}, contactItemElements),
                    React.createElement(ContactForm, {
                        value: this.props.newContact,
                        onChange: this.props.onChangeContact,
                        onSubmit: this.props.onSubmitContact,
                    })
                )
            );
        },
    });


    /*
     * Constants
     */


    var CONTACT_TEMPLATE = {name: "", email: "", description: "", errors: null};



    /*
     * Actions
     */


    function updateNewContact(contact) {
        setState({ newContact: contact });
    }


    function submitNewContact() {
        var contact = Object.assign({}, state.newContact, {key: (state.contacts.length + 1) + '', errors: {}});

        if (!contact.name) {
            contact.errors.name = ["Please enter your new contact's name"];
        }
        if (!/.+@.+\..+/.test(contact.email)) {
            contact.errors.email = ["Please enter your new contact's email"];
        }

        setState(
            Object.keys(contact.errors).length === 0
                ? {
                newContact: Object.assign({}, CONTACT_TEMPLATE),
                contacts: state.contacts.slice(0).concat(contact),
            }
                : { newContact: contact }
        );
    }


    function navigated() {
        setState({location: window.location.hash});
    }


    /*
     * Model
     */


// Set the initial app state
    var state = {
        contacts: [
            {key: '1', name: "James K Nelson", email: "james@jamesknelson.com", description: "Front-end Unicorn"},
            {key: '2', name: "Jim", email: "jim@example.com"},
        ],
        newContact: Object.assign({}, CONTACT_TEMPLATE),
        location: window.location.hash
    };

// Make the given changes to the state and perform any required housekeeping
    function setState(changes) {
        var component;

        Object.assign(state, changes);

        switch (state.location) {
            case '#/contacts':
                component = React.createElement(ContactsView, Object.assign({}, state, {
                    onChangeContact: updateNewContact,
                    onSubmitContact: submitNewContact,
                }));
                break;
            default:
                component = React.createElement('div', {},
                    React.createElement('h1', {}, "Not Found"),
                    React.createElement('a', {href: '#/contacts'}, "Contacts")
                );
        }

        ReactDOM.render(component, document.getElementById('react-app'));
    }

// Handle browser navigation events
    window.addEventListener('hashchange', navigated, false);

// Start the app
    navigated();











    initialize();
})();

