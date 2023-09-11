import {useSelector} from "react-redux";
import ChatMsg from "./ChatMsg";
import Grid from "@mui/material/Grid";
import {useEffect, useRef} from "react";
import {FormControl, IconButton, InputAdornment, TextField} from "@mui/material";
import {Send} from "@mui/icons-material";
import api from "../../service/api";
import {useParams} from "react-router-dom";


const Content = () => {
    const msgs = useSelector(sts => sts.message.msgs)
    const uId = useSelector(sts => sts.authentication.uId)
    const {convId} = useParams()


    // console.log(msgs)
    const chatMsgRef = useRef();

    const handleKeyDown = (event) => {
        if (event.key === 'Enter') {
            event.preventDefault(); // Prevents a newline from being added
            handleSendMsg(); // Call your submit function here
        }
    }
    const handleSendMsg = () => {
        const value = chatMsgRef.current.value

        if (value) {
            chatMsgRef.current.value = ''
            const now = new Date().getTime()
            api.post('/api/v1/message/chat/send', {
                content: value,
                sendAt: now,
                cId: convId
            })
        }
    }

    const chatMsgViewRef = useRef();
    useEffect(() => {
        console.log(chatMsgViewRef.current.scrollTop, chatMsgViewRef.current.scrollHeight)
        if(chatMsgViewRef.current.scrollTop <= chatMsgViewRef.current.scrollHeight - 20){
            chatMsgViewRef.current.scrollTop = chatMsgViewRef.current.scrollHeight
        }

    }, [msgs]);

    return <Grid container height='100%' direction='column'>
        <Grid item sx={{flex: 1, overflowY: 'auto'}} ref={chatMsgViewRef}>
            {
                msgs.map((value, index) => {

                    return (
                        <ChatMsg
                            key={index}

                            side={ uId === value.uId ? 'right' : null}
                            avatar={'https://mui.com/static/images/avatar/1.jpg'}
                            messages={[
                                value.content
                            ]}
                        />
                    )
                })
            }


        </Grid>
        <Grid item>
            <FormControl sx={{width: '100%'}}>
                <TextField
                    variant='outlined'
                    placeholder='Put some text...'
                    fullWidth
                    size='small'
                    inputRef={chatMsgRef}
                    onKeyDown={handleKeyDown}
                    InputProps={{
                        endAdornment: (
                            <InputAdornment position="end">
                                <IconButton
                                    aria-label="toggle password visibility"
                                    onClick={handleSendMsg}
                                >
                                    <Send/>
                                </IconButton>
                            </InputAdornment>
                        )
                    }}
                />
            </FormControl>
        </Grid>
    </Grid>
}
export default Content