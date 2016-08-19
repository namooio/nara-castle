/**
 * Created by hkkang on 2016-07-09.
 */

import React, { Component, PropTypes } from 'react';
import jQuery from 'jquery';
import { Ajax as NaraAjax } from './nara-common.js';


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
let File = {};

// Downloader common static component
class FileDownloader extends Component {
    //
    static requestDownload(fileId, model) {
        //
        let url = FileDownloader.url.DOWNLOAD_FILE.replace('{naraFileId}', fileId);

        NaraAjax
            .getJSON(url)
            .done( function (resultNaraFile) {
                //
                model.setState({ naraFile: resultNaraFile, type: 'DATA_URL' });

                if (!resultNaraFile.type) {
                    console.warn(`[NaraFile] Invalid content type of file. -> ${resultNaraFile.type}`);
                }
            });
    }
    static requestUrl(fileId, model) {
        //
        let url = FileDownloader.url.GET_FILE_URL.replace('{naraFileId}', fileId);

        NaraAjax
            .getJSON(url)
            .done( function (fileUrl) {
                //
                model.setState({ naraFileUrl: fileUrl, type: 'URL' });
            });
    }
    static getDownloaderInitailState() {
        //
        return {
            naraFile: null,     // { name, type, size, content },
            naraFileUrl: null,  // string
            type: null          // DATA_URL or URL
        }
    }
    static getFileDataUrl(contentType) {
        //
        return `data:${contentType};base64,`;
    }
    static isNotRenderable(model) {
        //
        let fileType;

        if (!model.state.naraFileUrl && !model.state.naraFile) {
            return true;
        }

        if (model.state.type === 'DATA_URL') {
            fileType = model.state.naraFile.type;
            if (!fileType || !fileType.includes('image')) {
                console.warn(`[NaraFile Downloader] Invalid file content type. -> ${fileType}`);
            }
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
    DOWNLOAD_FILE: '/pavilion-api/files/{naraFileId}',
    GET_FILE_URL: '/pavilion-api/files/{naraFileId}/url'
};



class ImageDownloader extends Component {
    //
    constructor(props) {
        //
        super(props);

        this.state = FileDownloader.getDownloaderInitailState();
    }
    // overriding
    componentDidMount() {
        //
        if (this.props.fileId) {
            // FileDownloader.requestDownload(this.props.fileId, this);
            FileDownloader.requestUrl(this.props.fileId, this);
        }
    }
    componentWillReceiveProps(nextProps) {
        //
        if (nextProps.fileId) {
            // FileDownloader.requestDownload(nextProps.fileId, this);
            FileDownloader.requestUrl(nextProps.fileId, this);
        }
    }
    render() {
        //
        if (FileDownloader.isNotRenderable(this)) {
            return null;
        }

        let src = null;

        if (this.state.type === 'URL') {
            src = this.state.naraFileUrl;
        }
        else if (this.state.type === 'DATA_URL') {
            src = FileDownloader.getFileDataUrl(this.state.naraFile.type) + this.state.naraFile.content;
        }

        return (
            <img src={src}
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
    height: PropTypes.node
};
ImageDownloader.defaultProps = {
    //
    fileId: null,
    className: null,
    width: null,
    height: null
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
    componentDidMount() {
        //
        if (this.props.fileId) {
            // FileDownloader.requestDownload(this.props.fileId, this);
            FileDownloader.requestUrl(this.props.fileId, this);
        }
    }
    componentWillReceiveProps(nextProps) {
        //
        if (nextProps.fileId) {
            // FileDownloader.requestDownload(nextProps.fileId, this);
            FileDownloader.requestUrl(nextProps.fileId, this);
        }
    }
    render() {
        //
        if (FileDownloader.isNotRenderable(this)) {
            return null;
        }

        let linkName = this.props.linkName || (this.state.naraFile ? this.state.naraFile.name : 'File link'),
            href = null;


        if (this.state.type === 'URL') {
            href = this.state.naraFileUrl;
        }
        else if (this.state.type === 'DATA_URL') {
            href = FileDownloader.getFileDataUrl(this.state.naraFile.type) + this.state.naraFile.content;
        }

        return (
            <a href={href}
               className={this.props.className} target="_blank" >
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



class ZipDownloader {
    //
    static requestZipFile(param, successCallback, failCallback) {
        //
        NaraAjax
            .postJSON(ZipDownloader.url.REQUEST_ZIP_FILE, param)
            .done( function (naraFileId) {
                //
                if (naraFileId) {
                    successCallback(ZipDownloader.url.DOWNLOAD_ZIP_FILE.replace('{naraFileId}', naraFileId));
                } else {
                    failCallback();
                }
            })
            .fail( function () {
                failCallback();
            });
    }
}

ZipDownloader.url = {
    //
    REQUEST_ZIP_FILE: '/pavilion-api/files/request-zip',
    DOWNLOAD_ZIP_FILE: '/pavilion-api/files/zip/{naraFileId}'
};

File.ZipFileloader = ZipDownloader;


class FileUploader extends Component {
    //
    static deleteFile() {
        // Will replace function in component
    }
    static _getInitialState() {
        //
        return {
            files: [],
            fileInfos: [],
            processing: false
        };
    }
    constructor(props) {
        //
        super(props);

        this.state = FileUploader._getInitialState();

        this.fileOnChange = this.fileOnChange.bind(this);
        this._deleteFile = this._deleteFile.bind(this);
        this.processUpload = this.processUpload.bind(this);
        this.requestUpload = this.requestUpload.bind(this);

        FileUploader.deleteFile = this._deleteFile;
    }
    // overriding
    componentDidMount() {
        //
        if (this.props.startUpload === true) {
            this.processUpload();
        }
    }
    componentWillReceiveProps(nextProps) {
        //
        if (nextProps.startUpload === true) {
            this.processUpload();
        }
    }
    // event
    fileOnChange(event) {
        //
        let files = this.state.files,
            fileInfos = this.state.fileInfos;

        if (!this.props.fileAttachable || !files) {
            files = [];
            fileInfos = [];
        }


        [].forEach.call(event.target.files, function (file, index) {
            files.push(file);
            fileInfos.push({
                name: file.name,
                type: file.type,
                size: file.size
            });
        });

        this.setState({ files, fileInfos });


        // Execute file change listener from drama
        if (typeof this.props.onChangeFile === 'function') {
            this.props.onChangeFile(fileInfos);
        }
    }
    // custom
    _deleteFile(fileIndexs) {
        //
        if (!(Array.isArray(fileIndexs) || typeof fileIndexs === 'number')) {
            console.error(`[NaraFileUploader] Invalid parameter of delete file -> ${fileIndexs}`);
            return;
        }

        if (typeof fileIndexs === 'number') {
            fileIndexs = [fileIndexs];
        }


        let files = this.state.files,
            fileInfos = this.state.fileInfos;

        fileIndexs.forEach( function (fileIndex) {
            files.splice(fileIndex, 1);
            fileInfos.splice(fileIndex, 1);
        });

        this.setState({ files, fileInfos });


        // Execute file change listener from drama
        if (typeof this.props.onChangeFile === 'function') {
            this.props.onChangeFile(fileInfos);
        }
    }
    processUpload() {
        //
        // onStartUpload에서 false를 리턴하면 중단
        if (typeof this.props.onStartUpload === 'function') {
            let uploadable = this.props.onStartUpload(this.state.fileInfos);
            if (uploadable === false) {
                return;
            }
        }

        // 선택된 파일이 없는 경우에는 업로드를 하지 않고 callback만 실행
        if (!this.state.files || this.state.files.length < 1) {
            if (typeof this.props.onSuccessUpload === 'function') {
                this.props.onSuccessUpload();
            }
            return;
        }


        this.setState({
            processing: true
        });

        // Create form data
        let fileForm = new FormData();

        [].forEach.call(this.state.files, function (file, index) {
            fileForm.append(`files[${index}]`, file);
        });

        // Add drama id
        let dramaId = this.props.dramaId;

        if (dramaId) {
            fileForm.append('dramaId', dramaId);
        }
        else {
            console.error(`[NaraFile] Invalid dramaId -> ${dramaId}`);
            return false;
        }

        // Add club id
        if (localStorage.getItem('clubId')) {
            fileForm.append('clubId', localStorage.getItem('clubId'));
        } else {
            console.error(`[NaraFile] Not exists club id in storage`);
            return;
        }



        let successCallback = function () {};

        if (typeof this.props.onSuccessUpload === 'function') {
            successCallback = this.props.onSuccessUpload;
        }


        this.requestUpload(fileForm, successCallback);
    }
    // request
    requestUpload(fileFormData, successCallback) {
        //
        jQuery.ajax({
            method: 'POST',
            url: FileUploader.url.UPLOAD_FILE,
            data: fileFormData,
            processData: false,
            contentType: false,
            success: function (resultFileIds) {
                //
                this.setState(FileUploader._getInitialState());

                if (!this.props.multiple && !this.props.fileAttachable) {
                    successCallback(resultFileIds[0]);
                }
                else {
                    successCallback(resultFileIds);
                }
            }.bind(this)
        });

    }
    render() {
        //
        let multiple = this.props.multiple === true;

        return (
            <input type="file" className={this.props.className} onChange={this.fileOnChange} multiple={multiple}/>
        );
    }
}

FileUploader.propTypes = {
    //
    dramaId: PropTypes.string.isRequired,
    startUpload: PropTypes.bool.isRequired,
    className: PropTypes.string,                // optional
    multiple: PropTypes.bool,                   // optional, default false
    fileAttachable: PropTypes.bool,             // optional, default false
    onStartUpload: PropTypes.func,              // optional
    onSuccessUpload: PropTypes.func,
    onChangeFile: PropTypes.func,               // optional
    deleteFile: PropTypes.func                  // optional, use only fileAttachable is true
};

FileUploader.defaultProps = {
    //
    dramaId: null,
    startUpload: null,
    multiple: false,
    fileAttachable: false,
    className: 'file'
};

FileUploader.url = {
    UPLOAD_FILE: '/pavilion-api/files'
};

File.Uploader = FileUploader;


export { File };



export default {
    Modal: NaraModal,
    File: File
};
