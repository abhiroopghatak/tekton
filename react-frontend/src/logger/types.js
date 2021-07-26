import Logger from './Logger';

export const Types = {
    MAIN_SCREEN_LOGGER: 1,
    FILTER_SCREEN_LOGGER: 2
}

export const LoggerRegisterInfo = [
    {
        id: Types.MAIN_SCREEN_LOGGER,
        level: Logger.Levels.INFO
    },
    {
        id: Types.FILTER_SCREEN_LOGGER,
        level: Logger.Levels.DEBUG
    },
]