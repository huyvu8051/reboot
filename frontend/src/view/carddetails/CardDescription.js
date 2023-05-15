import CheckList from '@editorjs/checklist'

import Embed from '@editorjs/embed'
import Table from '@editorjs/table'
import List from '@editorjs/list'
import Warning from '@editorjs/warning'
import Code from '@editorjs/code'
import LinkTool from '@editorjs/link'
import Image from '@editorjs/image'
import Raw from '@editorjs/raw'
import Header from '@editorjs/header'
import Quote from '@editorjs/quote'
import Marker from '@editorjs/marker'
import Delimiter from '@editorjs/delimiter'
import InlineCode from '@editorjs/inline-code'
import SimpleImage from '@editorjs/simple-image'
import * as React from "react";
import {useEffect, useRef} from "react";
import EditorJS from "@editorjs/editorjs";

import './CardDescription.css'

export const EDITOR_JS_TOOLS = {
    embed: Embed,
    table: Table,
    marker: Marker,
    list: List,
    warning: Warning,
    code: Code,
    linkTool: LinkTool,
    image: Image,
    raw: Raw,
    header: Header,
    quote: Quote,
    checklist: CheckList,
    delimiter: Delimiter,
    inlineCode: InlineCode,
    simpleImage: SimpleImage,
}

const CardDescription = () => {
    const editorContainerRef = useRef(null);
    let editorInstance = null;

    useEffect(() => {
        editorInstance = new EditorJS({
            holder: editorContainerRef.current,
            minHeight: '0px',
            placeholder: 'Type here...',
            tools: EDITOR_JS_TOOLS
        });
    }, []);

    return <>
        <h5 style={{padding: 0, margin: 0}}>
            Description
        </h5>
        <div style={{
            marginLeft: -5,
            padding: 5,
            borderRadius: 5,
            border: '2px solid #1976d2',
        }} ref={editorContainerRef}/>
    </>;
};
export default CardDescription