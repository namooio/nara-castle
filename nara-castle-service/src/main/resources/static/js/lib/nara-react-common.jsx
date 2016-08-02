/**
 * Created by hkkang on 2016-07-09.
 */

import React, { Component, PropTypes } from 'react';
import { Modal, Button } from 'react-bootstrap';
import jQuery from 'jquery';
import { Ajax as NaraAjax } from 'app/lib/nara-common.js';


// Modal
'use strict';

let modalPublicContext = {
    alert: null,
    confirm: null,
    openModal: null
};

// Define component
class NaraModal extends Component {
    //
    static alert(contentOrParams, handleOk) {
        //
        if (typeof modalPublicContext.alert === 'function') {
            modalPublicContext.alert(contentOrParams, handleOk);
        }
    }
    static confirm(contentOrParams, handleOk, handleCancel) {
        //
        if (typeof modalPublicContext.confirm === 'function') {
            modalPublicContext.confirm(contentOrParams, handleOk, handleCancel);
        }
    }
    static openModal(paramsObject) {
        //
        if (typeof modalPublicContext.openModal === 'function') {
            modalPublicContext.openModal(paramsObject);
        }
    }
    constructor(props) {
        //
        super(props);

        this.state = NaraModal.initialState;

        this.handleOk = this.handleOk.bind(this);
        this.handleCancel = this.handleCancel.bind(this);
        this._alert = this._alert.bind(this);
        this._confirm = this._confirm.bind(this);
        this._openModal = this._openModal.bind(this);

        modalPublicContext.alert = this._alert;
        modalPublicContext.confirm = this._confirm;
        modalPublicContext.openModal = this._openModal;
    }
    // event
    handleOk() {
        //
        let handleOk = this.state.handleOk;

        if (typeof handleOk === 'function') {
            handleOk();
        }
        this.setState(NaraModal.initialState);
    }
    handleCancel() {
        //
        let handleCancel = this.state.handleCancel;

        if (typeof handleCancel === 'function') {
            handleCancel();
        }
        this.setState(NaraModal.initialState);
    }
    // private custom
    _alert(contentOrParams, handleOk) {
        //
        // Content and handleOk
        if (contentOrParams && typeof contentOrParams === 'string') {
            this.setState({
                content: contentOrParams,
                handleOk: typeof handleOk === 'function' ? handleOk : NaraModal.initialState.handleOk
            });
        }
        // Parameters object
        else if (contentOrParams && typeof contentOrParams === 'object') {
            this._openModal(contentOrParams);
        }
        else {
            console.error('Invalid alert arguments');
        }

        this.setState({ show: true, type: NaraModal.type.ALERT });
    }
    _confirm(contentOrParams, handleOk, handleCancel) {
        //
        // Content and handleOk
        if (contentOrParams && typeof contentOrParams === 'string') {
            this.setState({
                content: contentOrParams,
                handleOk: typeof handleOk === 'function' ? handleOk : NaraModal.initialState.handleOk,
                handleCancel: typeof handleCancel === 'function' ? handleCancel : NaraModal.initialState.handleCancel
            });
        }
        // Parameters object
        else if (contentOrParams && typeof contentOrParams === 'object') {
            this._openModal(contentOrParams);
        }
        else {
            console.error('Invalid confirm arguments')
        }

        this.setState({ show: true, type: NaraModal.type.CONFIRM });
    }
    _openModal(paramsObject) {
        //
        let { title, content, handleOk, handleCancel } = paramsObject;
        this.setState({
            content,
            title: typeof title === 'string' ? title : NaraModal.initialState.title,
            handleOk: typeof handleOk === 'function' ? handleOk : NaraModal.initialState.handleOk,
            handleCancel: typeof handleCancel === 'function' ? handleCancel : NaraModal.initialState.handleCancel
        });
    }
    render() {
        //
        if (this.state.show !== true) {
            return (null);
        }
        return (
            <div>
                <div className="modal-container">
                    <div className="modal fade in" id="myModal" role="dialog" style={{display: 'block'}}>
                        <div className="modal-dialog">
                            <div className="modal-content">
                                <div className="modal-header">
                                    <button type="button" className="close" data-dismiss="modal">×</button>
                                    <h4 className="modal-title">{this.state.title}</h4>
                                </div>
                                <div className="modal-body">
                                    <p>{this.state.content}</p>
                                </div>
                                <div className="modal-footer">
                                    { this.state.type === NaraModal.type.ALERT && this.state.options.okUsable === true ?
                                        <button type="button" className="btn btn-default" data-dismiss="modal" onClick={this.handleOk}>Ok</button>
                                        : null
                                    }
                                    { this.state.type === NaraModal.type.CONFIRM ?
                                        <div>
                                            <button type="button" className="btn btn-default" data-dismiss="modal" onClick={this.handleCancel}>Cancel</button>
                                            <button type="button" className="btn btn-info" data-dismiss="modal" onClick={this.handleOk}>Ok</button>
                                        </div>
                                        : null
                                    }
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div className="modal-backdrop fade in"></div>
            </div>
        );
    }
}

NaraModal.initialState = {
    show: false,        // boolean
    type: null,         // enum
    title: 'Notice',   // string
    content: null,      // string or html
    handleOk: null,     // function
    handleCancel: null, // function
    options: {
        okUsable: true
    }
};

NaraModal.type = {
    ALERT: 'ALERT',
    CONFIRM: 'CONFIRM',
    CUSTOM: 'CUSTOM'
};

export { NaraModal as Modal };



// File
class File extends Component {
    //
    constructor(props) {
        //
        super(props);
    }
    render() {
        //
        if (this.props.defaultProps === File.mode.DOWNLOAD) {
            return (<FileDownloader />);
        }
        else {
            return (<FileUploader  />);
        }
    }
}

File.mode = {
    DOWNLOAD: 'Download',
    UPLOAD: 'Upload'
};

File.defaultProps = {
    mode: File.mode.DOWNLOAD
};


// Downloader common static component
class FileDownloader extends Component {
    //
    static requestDownload(fileId, model) {
        //
        NaraAjax
            .getJSON(FileDownloader.url.DOWNLOAD_FILE.replace('{naraFileId}', fileId))
            .done( function (resultNaraFile) {
                //
                //model.setState({ naraFile: resultNaraFile, fileDataUrl:  `data:${resultNaraFile.type};base64,` });
                model.setState({ naraFile: resultNaraFile });

                console.debug(`Download file result -> name: ${resultNaraFile.name}, type: ${resultNaraFile.type}, size: ${resultNaraFile.size}`);
                if (!resultNaraFile.type) {
                    console.warn(`[NaraFile] Invalid content type of file. -> ${resultNaraFile.type}`);
                }
            });
    }
    static getDownloaderInitailState() {
        //
        return {
            naraFile: null      // { name, type, size, content }
        }
    }
    static getFileDataUrl(contentType) {
        //
        return `data:${contentType};base64,`;
    }
    static isNotRenderable(model) {
        //
        let fileType;

        if (!model.state.naraFile) {
            return true;
        }

        fileType = model.state.naraFile.type;
        if (!fileType || !fileType.includes('image')) {
            console.warn(`[NaraFile Downaloder] Invalid file content type. -> ${fileType}`);
        }
        return false;
    }
    constructor(props) {
        //
        super(props);
    }
    render() {
        //
        return null;
    }
}

FileDownloader.url = {
    //
    DOWNLOAD_FILE: '/pavilion-api/files/{naraFileId}'
};



class ImageDownloader extends Component {
    //
    constructor(props) {
        //
        super(props);

        this.state = FileDownloader.getDownloaderInitailState();
    }
    // overriding
    componentWillReceiveProps(nextProps) {
        //
        if (nextProps.fileId) {
            FileDownloader.requestDownload(nextProps.fileId, this);
        }
    }
    render() {
        //
        if (FileDownloader.isNotRenderable(this)) {
            return null;
        }
        return (
            <img src={FileDownloader.getFileDataUrl(this.state.naraFile.type) + this.state.naraFile.content}
                 className={this.props.className}
                 width={this.props.width}
                 height={this.props.height}
            />
        );
    }
}

ImageDownloader.propTypes = {
    //
    fileId: PropTypes.string,
    className: PropTypes.string,
    width: PropTypes.node,
    heigth: PropTypes.node
};
ImageDownloader.defaultProps = {
    //
    fileId: null,
    className: null,
    width: null,
    heigth: null
};

File.ImageLoader = ImageDownloader;



class LinkDownloader extends Component {
    //
    constructor(props) {
        //
        super(props);

        this.state = FileDownloader.getDownloaderInitailState();
    }
    // overriding
    componentWillReceiveProps(nextProps) {
        //
        if (nextProps.fileId) {
            FileDownloader.requestDownload(nextProps.fileId, this);
        }
    }
    render() {
        //
        if (FileDownloader.isNotRenderable(this)) {
            return null;
        }

        let linkName = this.props.linkName || (this.state.naraFile ? this.state.naraFile.name : 'File link');
        return (
            <a href={FileDownloader.getFileDataUrl(this.state.naraFile.type) + this.state.naraFile.content}
               className={this.props.className}>
                    {linkName}
            </a>
        );
    }
}

LinkDownloader.propTypes = {
    //
    fileId: PropTypes.string,
    className: PropTypes.string,
    linkName: PropTypes.string
};
LinkDownloader.defaultProps = {
    //
    fileId: null,
    className: null,
    linkName: null
};

File.LinkLoader = LinkDownloader;



class FileUploader extends Component {
    //
    constructor(props) {
        //
        super(props);

        this.state = {
            fileForm: null,     // { files[], dramaId, clubId }
            processing: false
        };

        this.fileOnChange = this.fileOnChange.bind(this);
        this.fileOnSubmit = this.fileOnSubmit.bind(this);
        this.processUpload = this.processUpload.bind(this);
        this.requestUpload = this.requestUpload.bind(this);
    }
    // overriding
    componentWillReceiveProps(nextProps) {
        //
        if (nextProps.startUpload === true) {
            this.processUpload();
        }
    }
    // event
    fileOnChange(event) {
        //
        /*
         const _this = this;

         [].forEach.call(event.target.files, function (file) {
         let reader = new FileReader();

         reader.onload = (fileEvent) => {
         let files = _this.state.files;

         files.push({
         dataUrl: fileEvent.target.result,
         name: file.name,
         type: file.type
         });
         _this.setState({ files });
         };
         reader.readAsDataURL(file);
         });
         //*/

        //*
        let fileForm = new FormData();

        [].forEach.call(event.target.files, function (file, index) {
            fileForm.append(`files[${index}]`, file);
        });

        let dramaId = this.props.dramaId;

        if (dramaId) {
            fileForm.append('dramaId', dramaId);
        }
        else {
            console.error(`[NaraFile] Invalid dramaId -> ${dramaId}`);
            return false;
        }

        if (localStorage.getItem('clubId')) {
            fileForm.append('clubId', localStorage.getItem('clubId'));
        } else {
            fileForm.append('clubId', '02-00D');
        }

        this.setState({ files: fileForm });
        //*
    }
    fileOnSubmit(event) {
        //
        event.preventDefault();
        this.processUpload();
    }
    // custom
    processUpload() {
        //
        if (this.props.onStartUpload) {
            let uploadable = this.props.onStartUpload();
            if (uploadable === false) {
                return;
            }
        }

        this.setState({
            processing: true
        });

        let successCallback = function () {};
        if (typeof this.props.onSuccessUpload === 'function') {
            successCallback = this.props.onSuccessUpload;
        }
        this.requestUpload(successCallback);
    }
    // request
    requestUpload(successCallback) {
        //
        /*
         NaraAjax
         .postJSON('/files', this.state.files)
         .done(function (result) {
         console.log('Complete ajax -> ' +  result);
         });
         */
        //jQuery.post('/files', this.state.files, function (result) {
        //    console.log('Complete ajax -> ' +  result);
        //});
        jQuery.ajax({
            method: 'POST',
            url: FileUploader.url.UPLOAD_FILE,
            data: this.state.files,
            processData: false,
            contentType: false,
            success: function (resultFileIds) {
                console.log(`File ids: ${resultFileIds}`);

                if (this.props.multiple === true) {
                    successCallback(resultFileIds);
                }
                else {
                    successCallback(resultFileIds[0]);
                }
            }.bind(this)
        });

        /*
         const _this = this;
         let formData = new FormData();

         this.state.files.forEach( function (file, index) {
         formData.append(`files[${index}]`, JSON.stringify(file));
         });

         const promise = jQuery.ajax({
         url: '/file-form',
         type: "POST",
         data: formData,
         //enctype: 'multipart/form-data',
         processData: false,
         contentType: false,
         dataType: 'json'
         });

         promise.done( function(data) {
         _this.setState({
         processing: false,
         uploadedUri: data.uri
         });
         });
         */
    }
    render() {
        //
        if (this.props.multiple === true) {
            return (<input type="file" className={this.props.className} onChange={this.fileOnChange} />);
        }
        else {
            return (<input type="file" className={this.props.className} onChange={this.fileOnChange} multiple="multiple" />);
        }
    }
}

FileUploader.propTypes = {
    //
    dramaId: PropTypes.string.isRequired,
    startUpload: PropTypes.bool.isRequired,
    multiple: PropTypes.bool,
    className: PropTypes.string,
    onStartUpload: PropTypes.func,
    onSuccessUpload: PropTypes.func.isRequired
};

FileUploader.defaultProps = {
    //
    dramaId: null,
    startUpload: null,
    multiple: false,
    className: 'file'
};

FileUploader.url = {
    UPLOAD_FILE: '/pavilion-api/files'
};

File.Uploader = FileUploader;


export { File };



export default {
    Modal: Modal,
    File: File
};
