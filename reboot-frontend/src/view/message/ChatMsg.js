import Avatar from '@mui/material/Avatar'
import Typography from '@mui/material/Typography'
import {withStyles} from '@mui/styles'
import cx from 'clsx'
import PropTypes from 'prop-types'
import React from 'react'
import defaultChatMsgStyles from './defaultChatMsgStyles'

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
        <div
            style={{
                display: 'flex',
                justifyContent: side === 'right' ? 'flex-end' : 'flex-start'
            }}
        >
            <div style={{flexShrink: 0, display: 'flex', alignItems: 'flex-end', padding: 4}}>
                {side === 'left' && (
                    <Avatar
                        src={avatar}
                        className={cx(classes.avatar, AvatarProps.className)}
                        style={{
                            transition: 'bottom 0.3s ease'
                        }}

                    />
                )}
            </div>
            <div style={{
                flexGrow: 1
            }}>
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
            </div>

        </div>
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