import {FormControl, InputLabel, MenuItem, Select} from '@mui/material'
import * as React from 'react'
import {useEffect, useState} from 'react'
import api from '../../../service/api'
import {useNavigate, useParams} from "react-router-dom";

const CreateBoardWpSelect = (props) => {
    const [value, setValue] = useState('')
    const [wps, setWps] = useState([])
    const {wpId} = useParams();
    useEffect(() => {
        api.post('/api/v1/user/workspace')
            .then(resp => {
                setWps(resp.data)
                setValue(wpId)
            })
    }, [wpId])

    const handleChange = (event) => {
        setValue(event.target.value)
    }

    return (
        <>
            <FormControl fullWidth>
                <InputLabel id='demo-simple-select-label' size='small' shrink>Workspace</InputLabel>
                <Select
                    labelId='demo-simple-select-label'
                    id='demo-simple-select'
                    label='Workspace'
                    sx={{mb: 1}}
                    size='small'
                    fullWidth
                    notched
                    value={value}
                    onChange={handleChange}
                    inputRef={props.childRef}
                >
                    {
                        wps.length === 0 && (
                            <MenuItem>
                                No workspace exist
                            </MenuItem>)
                    }
                    {
                        wps.map(item => (
                            <MenuItem value={item.id}
                                      key={item.id}>
                                {item.title}
                            </MenuItem>
                        ))
                    }


                </Select>
            </FormControl>
        </>
    )
};
export default CreateBoardWpSelect