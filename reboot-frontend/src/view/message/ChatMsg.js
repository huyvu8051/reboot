import React from 'react'
import {withStyles} from '@mui/styles'
import defaultChatMsgStyles from './defaultChatMsgStyles'
import Grid from '@mui/material/Grid'
import Avatar from '@mui/material/Avatar'
import Typography from '@mui/material/Typography'
import PropTypes from 'prop-types'
import cx from 'clsx'

const ChatMsg = withStyles(defaultChatMsgStyles, {name: 'ChatMsg'})(props => {
    const {
        classes,
        avatar,
        messages,
        side,
        GridContainerProps,
        GridItemProps,
        AvatarProps,
        getTypographyProps
    } = props
    const attachClass = index => {
        if (index === 0) {
            return classes[`${side}First`]
        }
        if (index === messages.length - 1) {
            return classes[`${side}Last`]
        }
        return ''
    }
    return (
        <Grid
            container
            spacing={2}
            justify={side === 'right' ? 'flex-end' : 'flex-start'}
        >
            <Grid item xs={1} container alignItems='flex-end' >
                {side === 'left' && (
                    <Avatar
                        src={avatar}
                        className={cx(classes.avatar, AvatarProps.className)}
                        style={{
                            transition: 'bottom 0.3s ease'
                        }}
                    />
                )}
            </Grid>
            <Grid item xs={11}>
                {messages.map((msg, i) => {
                    const TypographyProps = getTypographyProps(msg, i, props)
                    return (
                        <div key={msg.id || i} className={classes[`${side}Row`]}>
                            <Typography
                                align={'left'}
                                {...TypographyProps}
                                className={cx(
                                    classes.msg,
                                    classes[side],
                                    attachClass(i),
                                    TypographyProps.className
                                )}
                            >
                                {msg}
                            </Typography>
                        </div>
                    )
                })}
            </Grid>

        </Grid>
    )
})

ChatMsg.propTypes = {
    avatar: PropTypes.string,
    messages: PropTypes.arrayOf(PropTypes.string),
    side: PropTypes.oneOf(['left', 'right']),
    GridContainerProps: PropTypes.shape({}),
    GridItemProps: PropTypes.shape({}),
    AvatarProps: PropTypes.shape({}),
    getTypographyProps: PropTypes.func
}
ChatMsg.defaultProps = {
    avatar: '',
    messages: [],
    side: 'left',
    GridContainerProps: {},
    GridItemProps: {},
    AvatarProps: {},
    getTypographyProps: () => ({})
}

export default ChatMsg