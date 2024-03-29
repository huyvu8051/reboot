const defaultChatMessageStyle = ({palette, spacing}) => {
    const radius = spacing(2.5)
    const size = spacing(4)
    const rightBgColor = palette.primary.main
    return {
        avatar: {
            width: size,
            height: size
        },
        leftRow: {
            textAlign: 'left'
        },
        rightRow: {
            textAlign: 'right'
        },
        msg: {
            padding: spacing(1, 2),
            borderRadius: 4,
            marginBottom: 4,
            display: 'inline-block',
            wordBreak: 'break-word',
            fontFamily: '-apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Helvetica, Arial, sans-serif, "Apple Color Emoji", "Segoe UI Emoji", "Segoe UI Symbol"',
            fontSize: '14px'
        },
        left: {
            borderTopRightRadius: radius,
            borderBottomRightRadius: radius,
            backgroundColor: palette.grey[100]
        },
        right: {
            borderTopLeftRadius: radius,
            borderBottomLeftRadius: radius,
            backgroundColor: rightBgColor,
            color: palette.common.white
        },
        leftFirst: {
            borderTopLeftRadius: radius
        },
        leftLast: {
            borderBottomLeftRadius: radius
        },
        rightFirst: {
            borderTopRightRadius: radius
        },
        rightLast: {
            borderBottomRightRadius: radius
        }
    }
}
export default defaultChatMessageStyle