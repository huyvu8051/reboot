import * as React from 'react'
import {useEffect, useRef, useState} from 'react'
import EditorJS from '@editorjs/editorjs'

import './CardDescription.css'

import config from './editor-js-tools-config'

const CardDescription = () => {
    const editorContainerRef = useRef()

    const [focused, setFocused] = useState(false)

    useEffect(() => {
        const editor = new EditorJS({
            logLevel: 'ERROR',
            holder: editorContainerRef.current,
            minHeight: '0px',
            placeholder: 'Type here...',
            tools: config
        })
        const editorElement = editorContainerRef.current
        const onFocusIn = () => setFocused(true)
        const onFocusOut = () => setFocused(false)
        editorElement.addEventListener("focusin", onFocusIn)
        editorElement.addEventListener("focusout", onFocusOut)
        return () => {
            editorElement.removeEventListener("focusin", onFocusIn)
            editorElement.removeEventListener("focusout", onFocusOut)
        }

    }, [])

    console.log('render')
    return <>
        <h5 style={{padding: 0, margin: 0}}>
            Description
        </h5>
        <div style={{
            marginLeft: -5,
            padding: 5,
            borderRadius: 5,
            border: focused ? '2px solid #1976d2' : 0,
        }} ref={editorContainerRef}/>
    </>
}
export default CardDescription